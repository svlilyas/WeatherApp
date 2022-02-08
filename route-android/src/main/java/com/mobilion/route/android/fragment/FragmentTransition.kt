package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route

/**
 * # FragmentTransition
 * Represents a way of hooking into the [FragmentRoute] to setup transitions before the
 * routing of a fragment is executed
 *
 * ## Usage
 * ### Simple Slide (Going from a `HomeRoute` to a `SettingsRoute`)
 *
 * ```
 * class HomeToSettingsTransition: FragmentTransition {
 *
 *     override un setup(
 *         transaction: FragmentTransaction,
 *         exitFragment: Fragment, exitRoute: Route,
 *         enterFragment: Fragment, enterRoute: Route
 *     ) {
 *         if(exitFragment is HomeFragment && enterFragment is SettingsFragment) {
 *            exitFragment.exitTransition = Slide(Gravity.LEFT)
 *            enterFragment.enterTransition = Slide(Gravity.RIGHT)
 *         }
 *
 *         if(exitFragment is SettingsFragment && enterFragment is HomeFragment) {
 *            exitFragment.exitTransition = Slide(Gravity.LEFT)
 *            enterFragment.enterTransition = Slide(Gravity.RIGHT)
 *         }
 *     }
 * }
 * ```
 *
 * ### Transitions can be chained
 * Transitions can be combined/chained to build a new transition which invokes all setup methods
 *
 * ```
 * val loginToRegisterTransition: FragmentTransition = ...
 * val loginToHomeTransition: FragmentTransition = ...
 * val homeToSettingsTransition: FragmentTransition = ...
 * val transitionSet: FragmentTransition = loginToRegisterTransition + loginToHomeTransition + homeToSettingsTransition
 * ```
 *
 *
 * ## Note
 * - The setup method will be called before the the route commits any fragment transaction
 * - The setup method will be called for pushing a new route to the top
 * - The setup method will be called for pop the current top route
 * - The setup method won't be called for changes to the routing stack that don't affect the top route
 * - The transaction parameter should not be used for anything different than setting up transitions
 *
 * @see GenericFragmentTransition
 */
typealias FragmentTransition = GenericFragmentTransition<Fragment, Route, Fragment, Route>
