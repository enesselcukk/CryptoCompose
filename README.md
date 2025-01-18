# BtcCompose 📱

BtcCompose is a modern Bitcoin tracking application developed using Jetpack Compose. This app allows you to monitor cryptocurrency data in real-time.

## Features ✨

- Real-time Bitcoin price tracking
- Price change graph visualization with custom Canvas animations
- Interactive price charts with gestures
- Custom animations and transitions
- Price display in different currencies
- Modern and user-friendly interface
- Custom Canvas components
  - Animated price change indicators
  - Interactive chart tooltips
  - Custom loading animations
  - Particle effects for price alerts

## Technical Specifications 🔧

### Architecture
- Clean Architecture
- MVVM (Model-View-ViewModel)
- Repository Pattern
- Use Case Pattern
- Single Activity Pattern

### Package Structure 📁
📦 app
┣ 📂 di
┃ ┣ 📜 AppModule
┃ ┣ 📜 NetworkModule
┃ ┗ 📜 RepositoryModule
┣ 📂 data
┃ ┣ 📂 remote
┃ ┃ ┣ 📂 api
┃ ┃ ┃ ┗ 📜 CoinApi
┃ ┃ ┣ 📂 dto
┃ ┃ ┃ ┗ 📜 CoinDto
┃ ┃ ┗ 📂 repository
┃ ┃ ┗ 📜 CoinRepositoryImpl
┃ ┗ 📂 local
┃ ┣ 📂 dao
┃ ┗ 📂 entity
┣ 📂 domain
┃ ┣ 📂 model
┃ ┃ ┗ 📜 Coin
┃ ┣ 📂 repository
┃ ┃ ┗ 📜 CoinRepository
┃ ┗ 📂 use_case
┃ ┗ 📜 GetCoinsUseCase
┗ 📂 presentation
┣ 📂 ui
┃ ┣ 📂 theme
┃ ┣ 📂 components
┃ ┗ 📂 screens
┃ ┣ 📂 home
┃ ┗ 📂 detail
┣ 📂 viewmodel
┗ 📂 state

#### Package Details

📂 **di** (Dependency Injection)
- Contains Hilt modules for dependency injection setup
- Manages app-wide dependencies and their lifecycle

📂 **data**
- 📂 **remote**: API interfaces, DTOs and network-related implementations
  - 📂 **api**: REST API interface definitions
  - 📂 **dto**: Data Transfer Objects for network responses
  - 📂 **repository**: Implementation of repository interfaces
- 📂 **local**: Local database related components
  - 📂 **dao**: Data Access Objects for Room
  - 📂 **entity**: Database entities

📂 **domain**
- 📂 **model**: Domain models/entities
- 📂 **repository**: Repository interfaces
- 📂 **use_case**: Business logic use cases

📂 **presentation**
- 📂 **ui**: All UI-related components
  - 📂 **theme**: App theme, colors, typography
  - 📂 **components**: Reusable Compose components
  - 📂 **screens**: Screen-specific composables
- 📂 **viewmodel**: ViewModels for each screen
- 📂 **state**: UI state holders and events


### Technologies and Libraries 🛠

#### Core Technologies
- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI toolkit
- **Coroutines** - For asynchronous operations
- **Flow** - For reactive programming
- **StateFlow** - For UI state management

#### Dependency Injection
- **Hilt** - For dependency injection

#### Networking
- **Retrofit** - REST API communication
- **OkHttp** - HTTP Client
- **Gson** - JSON serialization/deserialization

#### Visual Components
- **Coil** - Image loading
- **Accompanist** - Compose helper libraries

#### Database
- **Room** - Local database
- **DataStore** - Key-value data storage

#### Testing
- **JUnit** - Unit tests
- **Mockk** - Kotlin mocking library
- **Turbine** - Flow testing
- **Truth** - Test assertions
- **UI Tests**
*The project includes comprehensive UI tests using:*
   - Compose UI Testing
   - Screenshot Testing
   - Semantic Testing

## Screenshots 📸

https://github.com/user-attachments/assets/c989c34d-501f-46d2-a3fc-47d04693fc07
