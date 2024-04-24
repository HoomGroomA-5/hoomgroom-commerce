package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(new Product());

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Test Product\",\"productDescription\":\"Test Description\",\"productImage\":\"Test Image\",\"productPrice\":100.0,\"productDiscountPrice\":90.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.findById(any(UUID.class))).thenReturn(new Product());
        when(productService.editProduct(any(Product.class))).thenReturn(new Product());

        mockMvc.perform(put("/products/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Test Product\",\"productDescription\":\"Test Description\",\"productImage\":\"Test Image\",\"productPrice\":100.0,\"productDiscountPrice\":90.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(productService.findById(any(UUID.class))).thenReturn(new Product());
        when(productService.deleteProduct(any(UUID.class))).thenReturn(new Product());

        mockMvc.perform(delete("/products/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}