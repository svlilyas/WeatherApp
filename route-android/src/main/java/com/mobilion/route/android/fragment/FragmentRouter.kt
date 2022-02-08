package com.mobilion.weatherapproute.android.fragment

import android.os.Bundle
import androidx.annotation.AnyThread
import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.*
import com.mobilion.weatherapp.routecore.RoutingStack.Factory.empty
import com.mobilion.weatherapp.routecore.routes
import com.mobilion.weatherapproute.android.fragment.dsl.FragmentRouterBuilder
import com.mobilion.weatherapproute.android.fragment.dsl.FragmentRouterDsl
import com.mobilion.weatherapproute.android.fragment.internal.FragmentContainerLifecycle
import com.mobilion.weatherapproute.android.fragment.internal.FragmentElementImpl
import com.mobilion.weatherapproute.android.fragment.internal.FragmentRouterConfiguration
import com.mobilion.weatherapproute.android.utils.log
import com.mobilion.weatherapproute.android.utils.mainThread
import com.mobilion.weatherapproute.android.utils.requireMainThread

/**
 * # FragmentRouter
 * [Router] implementation that targets Android [Fragment]s
 *
 * ## DSL
 * This route can be configured using the [FragmentRouterDsl]:
 * e.g.
 * ```
 * val route = FragmentRouter {
 *
 *     // Define which fragment to show for which route
 *     routing {
 *         route<LoginRoute> { LoginFragment::class }
 *         route<HomeRoute> { HomeFragment::class }
 *         route<SettingsRoute> { SettingsFragment::class } 
 *     }
 *
 *     // Register beautiful transitions to make the app look and feel nice
 *     transitions {
 *         register(LoginToHomeTransition())
 *         register(HomeToSettingsTransition())
 *     }
 * }
 * ```
 *
 * ## Usage
 * ### Example: Replacing the `LoginRoute` with the `HomeRoute`:
 * ```
 * route { pop().push(HomeRoute()) } 
 * ```
 *
 * ### Example: Navigating to the `SettingsRoute`
 * ```
 * route.push(SettingsRoute())
 * ```
 *
 * ### Example: Going back from the `SettingsRoute`
 *
 * ```
 * route.pop()
 * ```
 *
 *
 * ## Note
 * - This route requires the `setup` method to be called which can only be called from
 * a [KompassFragmentActivity] or [KompassFragment].
 * - Instructions sent to the route before the `setup` method was called will be postponed until the route was set up
 * - This route (once set up) will automatically handle the lifecycle of its host and will only execute instructions
 * between configurable lifecycle-events (onResume <-> onPause by default).
 * - Instructions sent to the route while the lifecycle of the host is not suitable will be postponed and executed
 * once the lifecycle enters the correct state again (onResume by default)
 * - This route will automatically save its state for configuration changes or process-death
 *
 */
class FragmentRouter<T : Route> internal constructor(
    override val fragmentMap: FragmentMap<T>,
    override val fragmentRouteStorageSyntax: FragmentRouteStorageSyntax<T>,
    override val fragmentRoutingStackBundleSyntax: FragmentRoutingStackBundleSyntax<T>,
    private val fragmentTransition: FragmentTransition,
    private val fragmentStackPatcher: FragmentStackPatcher,
    fragmentContainerLifecycleFactory: FragmentContainerLifecycle.Factory,
    initialInstruction: RouterInstruction<T>
) :
    Router<T>,
    FragmentRouterConfiguration<T> {

    /**
     * Represents the whole state of this route
     */
    internal sealed class State<T : Route> {

        abstract val stack: RoutingStack<T>

        data class Attached<T : Route>(
            override val stack: RoutingStack<T>,
            val container: FragmentContainer
        ) : State<T>()

        data class Detached<T : Route>(
            override val stack: RoutingStack<T>,
            val pendingInstruction: RouterInstruction<T> = EmptyRouterInstruction()
        ) : State<T>()
    }

    internal val fragmentContainerLifecycle: FragmentContainerLifecycle =
        fragmentContainerLifecycleFactory(this)

    private var _state: State<T> = State.Detached(
        stack = empty(),
        pendingInstruction = initialInstruction
    )

    private var state: State<T>
        set(newState) {
            requireMainThread()
            val oldState = _state
            _state = newState
            onStateChanged(oldState = oldState, newState = newState)
        }
        get() {
            requireMainThread()
            return _state
        }

    /**
     * Will execute the given instruction on the main thread.
     * Execution will be done immediately if the calling thread is already the main thread.
     */
    @AnyThread
    override infix fun instruction(instruction: RouterInstruction<T>) = mainThread {
        state = state.nextState(instruction)
    }

    internal fun attachContainer(container: FragmentContainer) {
        requireMainThread()
         this.state = when (val state = this.state) {
            /* Expected case */
            is State.Detached<T> -> State.Attached(state.pendingInstruction(state.stack), container)

            /* Rare case, can happen if the same fragment is pushed again */
            is State.Attached<T> -> state.copy(container = container)
        }
    }

    internal fun detachContainer() {
        requireMainThread()
        val state = this.state as? State.Attached<T> ?: return
        this.state = State.Detached(state.stack)
    }

    internal fun saveState(outState: Bundle) {
        requireMainThread()
        fragmentRoutingStackBundleSyntax.run {
            state.stack.saveTo(outState)
        }
    }

    internal fun restoreState(outState: Bundle?) {
        requireMainThread()
        val stack = fragmentRoutingStackBundleSyntax.run { outState?.restore() } ?: empty()
        _state = when (val state = state) {
            is State.Attached -> state.copy(stack = stack)
            is State.Detached -> state.copy(stack = stack)
        }
    }

    private fun State<T>.nextState(instruction: RouterInstruction<T>): State<T> = when (this) {
        is State.Attached -> copy(stack = stack.instruction())
        is State.Detached -> copy(pendingInstruction = pendingInstruction + instruction)
    }

    private fun onStateChanged(oldState: State<T>, newState: State<T>) {
        if (newState is State.Attached<T>) {
            apply(oldState, newState)
        }
    }

    private fun apply(oldState: State<T>, newState: State.Attached<T>) {
        if (oldState.stack.elements == newState.stack.elements) {
            return
        }

        log("transition to stack: ${newState.stack.routes.joinToString(", ")}")
        fragmentStackPatcher(
            fragmentTransition,
            newState.container,
            oldState.stack,
            prepareFragmentStack(newState)
        )
    }

    private fun prepareFragmentStack(state: State.Attached<T>): FragmentRoutingStack<T> {
        val factory = FragmentElementImpl.Factory(this, state.container)
        return FragmentRoutingStack(state.stack.elements, factory)
    }

    companion object Factory {
        @FragmentRouterDsl
        inline operator fun <reified T : Route> invoke(init: FragmentRouterBuilder<T>.() -> Unit): FragmentRouter<T> {
            return FragmentRouterBuilder(T::class)

                .also(init)
                .build()
        }
    }
}
