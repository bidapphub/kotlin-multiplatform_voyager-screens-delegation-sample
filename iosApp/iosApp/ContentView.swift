import SwiftUI
import shared

struct ContentView: View {
	func makeUIViewController(context: Context) -> UIViewController {
            Main_iosKt.MainViewController()
        }

	func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}