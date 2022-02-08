package com.mobilion.weatherapp.features.cashier.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.mobilion.weatherapp.R
import com.mobilion.weatherapp.core.common.PermissionHelper
import com.mobilion.weatherapp.core.common.permissionmanager.PermissionHandler
import com.mobilion.weatherapp.core.extensions.observeEvent
import com.mobilion.weatherapp.core.platform.BaseFragment
import com.mobilion.weatherapp.core.router.PageName
import com.mobilion.weatherapp.databinding.CustomDialogBinding
import com.mobilion.weatherapp.databinding.FragmentCashierDashboardBinding
import com.mobilion.weatherapp.features.cashier.domain.viewevent.CashierDashboardViewEvent
import com.mobilion.weatherapp.features.cashier.domain.viewmodel.CashierDashboardViewModel
import com.mobilion.weatherapp.features.cashier.presentation.route.CashierDashboardRouter
import com.mobilion.weatherapproute.android.route
import dagger.hilt.android.AndroidEntryPoint
import eu.livotov.labs.android.camview.ScannerLiveView
import eu.livotov.labs.android.camview.ScannerLiveView.ScannerViewEventListener
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder
import timber.log.Timber

@AndroidEntryPoint
class CashierDashboardFragment :
    BaseFragment<FragmentCashierDashboardBinding, CashierDashboardViewModel>(
        layoutId = R.layout.fragment_cashier_dashboard,
        viewModelClass = CashierDashboardViewModel::class.java
    ) {
    val routes: CashierDashboardRouter.CashierDashboard by route()
    override fun getScreenKey(): String = PageName.PreLogin.CASHIER_DASHBOARD

    override fun onDataBinding() {
        binding.apply {
            viewmodel = viewModel
        }
        popBackRouter(true)
        observeEvent(viewModel.event, ::onViewEvent)

        fetchViewData()

        requestPermissions()
    }

    private fun fetchViewData() {
        viewModel.fetchProducts()

        viewModel.qrResultProduct.observe(viewLifecycleOwner, {
            if (it != null) {
                Timber.e(it.toString())
                binding.scannedTextView.text =
                    String.format(getString(R.string.product_found_1s), it.qrCode)
                showPaymentDialog()
            } else {
                binding.scannedTextView.text = getString(R.string.cant_found_product)
            }
        })
    }

    private fun requestPermissions() = PermissionHandler.requestPermission(
        this,
        123,
        PermissionHelper.cameraPermission
    ) {
        permissionsGranted()
    }

    private fun permissionsGranted() {
        binding.cameraView.scannerViewEventListener = object : ScannerViewEventListener {
            override fun onScannerStarted(scanner: ScannerLiveView) {
                // method is called when scanner is started
                Timber.e("Scanner Started")
            }

            override fun onScannerStopped(scanner: ScannerLiveView) {
                // method is called when scanner is stopped.
                Timber.e("Scanner Stopped")
            }

            override fun onScannerError(err: Throwable) {
                // method is called when scanner gives some error.
                Timber.e("Scanner Error : $err")
            }

            override fun onCodeScanned(data: String) {
                // method is called when camera scans the
                // qr code and the data from qr code is
                // stored in data in string format.
                if (data.contains("_")) {
                    data.split("_")[1]
                    viewModel.findProduct(data.split("_")[1])
                    binding.scannedTextView.text = data
                    logEventFirebase(
                        "qrReadSuccess",
                        bundleOf(Pair("qrReadSuccess", data))
                    )
                } else {
                    binding.scannedTextView.text = getString(R.string.cant_found_product)
                }
            }
        }
    }

    fun showPaymentDialog() {
        val dialogBinding: CustomDialogBinding =
            CustomDialogBinding.inflate(LayoutInflater.from(context));

        dialogBinding.viewmodel = viewModel

        val dialog =
            AlertDialog.Builder(requireContext()).setView(dialogBinding.root).setCancelable(true)

        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        val decoder = ZXDecoder()
        // 0.5 is the area where we have
        // to place red marker for scanning.
        // 0.5 is the area where we have
        // to place red marker for scanning.
        decoder.scanAreaPercent = 0.8
        // below method will set secoder to camera.
        // below method will set secoder to camera.
        binding.cameraView.decoder = decoder
        binding.cameraView.startScanner()
    }

    override fun onPause() {
        // on app pause the
        // camera will stop scanning.
        binding.cameraView.stopScanner();
        super.onPause()
    }

    private fun onViewEvent(event: CashierDashboardViewEvent) {
        when (event) {
            CashierDashboardViewEvent.ReadQrCode -> {
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?): CashierDashboardFragment {
            val fragment = CashierDashboardFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
