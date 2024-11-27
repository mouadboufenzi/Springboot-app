package ProductControllerTest;

import com.myapp.tutoriel.Exceptions.ProductNotValidException;
import com.myapp.tutoriel.Product.Model.Product;
import com.myapp.tutoriel.Product.ProductController;
import com.myapp.tutoriel.Product.ProductRepository;
import com.myapp.tutoriel.Product.commandhandlers.CreateProductCommandHandler;
import com.myapp.tutoriel.TutorielApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = TutorielApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductCommandHandlerTest_validProduct_returnsSuccess() {
        Product product = new Product();
        product.setId(1);
        product.setName("TestProductName");
        product.setDescription("Test");
        product.setPrice(1.0);
        product.setQuantity(1);

        ResponseEntity response = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandlerTest_invalidPrice_throwsInvalidPriceException() {
        Product product = new Product();
        product.setId(1);
        product.setName("TestProductName");
        product.setDescription("Test");
        product.setPrice(-11.0);
        product.setQuantity(1);

        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () ->  createProductCommandHandler.execute(product));
        assertEquals("Product price is invalid", exception.getSimpleResponse().getMessage());
    }
}
