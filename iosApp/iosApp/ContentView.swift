import SwiftUI
import shared

struct ContentView: View {
// 	let greet = Greeting().greet()

    @ObservedObject private(set) var viewModel : ViewModel

	var body: some View {
		Text(viewModel.text)
	}
}

extension ContentView {
    class ViewModel : ObserverObject {
        @Published var text = "Loading..."
        init() {
            Greeting().greeting { greeting, error in
                DispatchQueue.main.async {
                    if let greeting = greeting
                        self.text = greeting
                } else {
                    self.text = error?.localizedDescription ?? "error"
                }
            }
        }
    }
}

/*
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
*/

