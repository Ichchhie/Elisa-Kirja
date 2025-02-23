**Project Info:**
This is a Single Page Application built using Kotlin. The goal of this app was to test the alpha version of the latest Compose Multiplatform targeting WebAssembly(when this project was done i.e. Feb-June 2024) and document the technical challenges. Few problems encountered were also solved like this one: [open link](https://medium.com/@ichchhamoktan07/adding-ktor-to-my-kotlin-multiplatform-project-for-web-web-assembly-or-wasm-4f6adad39b73).

**Tech stack used:**
  - Jetpack Compose for UI
  - Lazy loading for rendering books
  - Ktor for API integration
    
**Code Structure:**

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

**Note:** Compose/Web is Experimental and may be changed at any time. Use it only for evaluation purposes.
We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserRun` Gradle task.
