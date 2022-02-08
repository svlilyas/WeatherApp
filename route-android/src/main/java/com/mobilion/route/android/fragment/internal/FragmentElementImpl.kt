package com.mobilion.weatherapproute.android.fragment.internal

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Key
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack
import com.mobilion.weatherapproute.android.fragment.FragmentContainer
import com.mobilion.weatherapproute.android.fragment.FragmentElement
import com.mobilion.weatherapproute.android.fragment.FragmentMappingMissingException
import com.mobilion.weatherapproute.android.fragment.FragmentRoute
import kotlin.reflect.KClass

internal class FragmentElementImpl<T : Route>(
    private val fragmentRouterConfiguration: FragmentRouterConfiguration<T>,
    private val container: FragmentContainer,
    private val element: RoutingStack.Element<T>
) :
    FragmentElement<T>(),
    FragmentRouterConfiguration<T> by fragmentRouterConfiguration {

    class Factory<T : Route>(
        private val fragmentRouterConfiguration: FragmentRouterConfiguration<T>,
        private val container: FragmentContainer
    ) : FragmentElement.Factory<T> {
        override fun invoke(element: RoutingStack.Element<T>): FragmentElement<T> {
            return FragmentElementImpl(
                fragmentRouterConfiguration = fragmentRouterConfiguration,
                container = container,
                element = element
            )
        }
    }

    override val key: Key = element.key

    override val route: T = element.route

    override fun createFragment(): Fragment {
        val context = container.activity
        val fragmentFactory = container.fragmentManager.fragmentFactory
        val fragment = fragmentFactory.instantiate(context.classLoader, getFragmentClassNameOrThrow())
        fragmentRouteStorageSyntax.run { fragment.attach(route) }
        return fragment
    }

    private fun getFragmentClassNameOrThrow(): String {
        return getFragmentClassOrThrow().java.canonicalName.orEmpty()
    }

    private fun getFragmentClassOrThrow(): KClass<out Fragment> {
        if (route is FragmentRoute) {
            return route.fragment
        }

        return fragmentMap[route] ?: throw FragmentMappingMissingException(route)
    }
}
