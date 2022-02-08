package com.mobilion.weatherapp.features.customer.presentation

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.mobilion.data.remote.response.Product
import com.mobilion.weatherapp.R
import com.mobilion.weatherapp.core.common.data.PaymentStatus
import com.mobilion.weatherapp.core.extensions.observeEvent
import com.mobilion.weatherapp.core.platform.BaseFragment
import com.mobilion.weatherapp.core.router.PageName
import com.mobilion.weatherapp.databinding.FragmentCustomerDashboardBinding
import com.mobilion.weatherapp.features.customer.domain.viewevent.CustomerDashboardViewEvent
import com.mobilion.weatherapp.features.customer.domain.viewmodel.CustomerDashboardViewModel
import com.mobilion.weatherapp.features.customer.presentation.route.CustomerDashboardRouter
import com.mobilion.weatherapproute.android.route
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CustomerDashboardFragment :
    BaseFragment<FragmentCustomerDashboardBinding, CustomerDashboardViewModel>(
        layoutId = R.layout.fragment_customer_dashboard,
        viewModelClass = CustomerDashboardViewModel::class.java
    ) {
    val routes: CustomerDashboardRouter.CustomerDashboard by route()
    override fun getScreenKey(): String = PageName.PreLogin.CUSTOMER_DASHBOARD

    var uniqueId: String = ""

    override fun onDataBinding() {
        binding.apply {
            viewmodel = viewModel
        }
        popBackRouter(true)
        observeEvent(viewModel.event, ::onViewEvent)
    }

    private fun qrReadListener() {
        viewModel.qrReadListener.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.apply {
                    if (it.id == uniqueId) {
                        when (it.paymentStatus) {
                            PaymentStatus.WAITING.name -> {
                                statusTextView.text = String.format(
                                    getString(R.string.status_1s),
                                    getString(R.string.payment_status_waiting)
                                )

                                statusTextView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.blue
                                    )
                                )

                                statusTextView.visibility = View.VISIBLE
                            }
                            PaymentStatus.PROCEED.name -> {
                                statusTextView.text = String.format(
                                    getString(R.string.status_1s),
                                    getString(R.string.payment_status_proceed)
                                )

                                statusTextView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.yellow
                                    )
                                )

                                statusTextView.visibility = View.VISIBLE
                            }
                            PaymentStatus.SUCCESS.name -> {
                                statusTextView.text = String.format(
                                    getString(R.string.status_1s),
                                    getString(R.string.payment_status_success)
                                )

                                statusTextView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )

                                statusTextView.visibility = View.VISIBLE
                            }
                            PaymentStatus.FAIL.name -> {
                                statusTextView.text = String.format(
                                    getString(R.string.status_1s),
                                    getString(R.string.payment_status_fail)
                                )

                                statusTextView.setBackgroundColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.red
                                    )
                                )

                                statusTextView.visibility = View.VISIBLE
                            }
                            else -> {
                                statusTextView.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        })
    }

    private fun generateQrCode() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        uniqueId = System.currentTimeMillis().toString()
        val qrCodeString = "product_$uniqueId"

        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        // generating dimension from width and height.
        var dimen = if (width < height) width else height
        dimen = dimen * 3 / 4

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        val qrgEncoder =
            QRGEncoder(qrCodeString, null, QRGContents.Type.TEXT, dimen)

        // getting our qrcode in the form of bitmap.
        val bitmap = qrgEncoder.encodeAsBitmap()
        // the bitmap is set inside our image
        // view using .setimagebitmap method.
        binding.qrCodeImageView.setImageBitmap(bitmap)

        viewModel.addProduct(
            Product(
                uniqueId,
                qrCodeString,
                "defaultName",
                PaymentStatus.WAITING.name
            )
        )

        viewModel.listenForQrRead(uniqueId)

        qrReadListener()

        logEventFirebase(
            "qrGeneratedSuccess",
            bundleOf(Pair("qrGenerateSuccess", qrCodeString))
        )
    }

    private fun onViewEvent(event: CustomerDashboardViewEvent) {
        when (event) {
            CustomerDashboardViewEvent.GenerateQrCode -> {
                try {
                    logEventFirebase(
                        "qrGenerateButtonClick",
                        bundleOf(Pair("qrGenerate", "QR CODE GENERATE"))
                    )

                    generateQrCode()
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?): CustomerDashboardFragment {
            val fragment = CustomerDashboardFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
