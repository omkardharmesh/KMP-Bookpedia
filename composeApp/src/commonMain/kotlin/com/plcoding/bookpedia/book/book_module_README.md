Here's the book module's data flow explained in 5 lines:

1. **BookRepository (interface)** defines the contract for accessing book data, returning domain models and hiding implementation details from ViewModels.

2. **DefaultBookRepository** implements this interface, orchestrates data retrieval, and transforms DTOs to domain models without knowing how data is actually fetched.

3. **KtorRemoteBookDataSource** handles the network operations, making API calls using Ktor client and returning DTOs wrapped in Result objects.

4. Data flows from network → DTOs → domain models → UI state, while errors propagate upward through the same chain.

5. This separation creates a maintainable architecture where each component has a single responsibility and can be replaced without affecting other layers.