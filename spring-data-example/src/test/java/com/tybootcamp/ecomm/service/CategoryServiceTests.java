package com.tybootcamp.ecomm.service;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.exceptions.BadRequest;
import com.tybootcamp.ecomm.exceptions.EntityNotFound;
import com.tybootcamp.ecomm.mocks.InMemoryCategoryRepository;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import com.tybootcamp.ecomm.services.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CategoryServiceTests {
    private static final long VALID_ID = 1;
    private static final String VALID_NAME = "Jeans";
    private static final String UPDATED_NAME = "T-Shirt";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    private final InMemoryCategoryRepository mockRepository = new InMemoryCategoryRepository();
    private final CategoryService experimentalCategoryService = new CategoryService(mockRepository);

    @BeforeEach
    void setUp() {
        openMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @AfterEach
    void tearDown() {
        mockRepository.clearDB();
    }

    @ParameterizedTest
    @MethodSource("illegalCategoryNames")
    void testAddNewCategory_illegalName_throwErr(String name, Class<?> exceptionInstance) {
        assertThatThrownBy(() -> categoryService.addNewCategory(name)).isInstanceOf(exceptionInstance);
    }

    @Test
    void testAddNewCategory_correctName_returnCategory() {
        when(categoryRepository.save(any())).thenReturn(new Category(4, VALID_NAME));

        assertThat(categoryService.addNewCategory(VALID_NAME))
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .matches(category -> category.getName().equals(VALID_NAME));
    }

    @ParameterizedTest
    @MethodSource("illegalCategoryNames")
    void testCategoryByName_illegalArgument_throwErr(String name, Class<?> exceptionInstance) {
        assertThatThrownBy(() -> categoryService.getCategoryByName(name)).isInstanceOf(exceptionInstance);
    }

    @Test
    void testCategoryByName_hasRecordInDB_returnCategory() {
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(new Category(4, VALID_NAME)));

        assertThat(categoryService.getCategoryByName(VALID_NAME))
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .matches(category -> category.getName().equals(VALID_NAME));
    }

    @Test
    void testCategoryByName_noRecordInDB_returnCategory() {
        when(categoryRepository.findByName(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.getCategoryByName(VALID_NAME)).isInstanceOf(EntityNotFound.class);
    }

    @ParameterizedTest
    @MethodSource("illegalCategoryObjects")
    void testUpdateCategory_illegalArgument_throwErr(Category updateCategory, Class<?> exceptionClass) {
        when(categoryRepository.findById(VALID_ID)).thenReturn(Optional.of(new Category(VALID_ID, VALID_NAME)));

        assertThatThrownBy(() -> categoryService.updateCategory(updateCategory)).isInstanceOf(exceptionClass);
    }

    @Test
    void testUpdateCategory() {
        mockRepository.save(new Category(VALID_ID, VALID_NAME));

        experimentalCategoryService.updateCategory(new Category(VALID_ID, UPDATED_NAME));

        Optional<Category> optionalCat = mockRepository.findByName(UPDATED_NAME);
        assertThat(optionalCat.get())
                .isNotNull()
                .matches(cat -> cat.getId() == VALID_ID && cat.getName().equals(UPDATED_NAME));
    }

    private static Stream<Arguments> illegalCategoryNames() {
        return Stream.of(
                Arguments.of(null, BadRequest.class),
                Arguments.of("  ", BadRequest.class)
        );
    }

    private static Stream<Arguments> illegalCategoryObjects() {
        return Stream.of(
                Arguments.of(null, BadRequest.class),
                Arguments.of(new Category(), BadRequest.class),
                Arguments.of(new Category(VALID_NAME), EntityNotFound.class),
                Arguments.of(new Category(VALID_ID, " "), BadRequest.class),
                Arguments.of(new Category(VALID_ID, null), BadRequest.class),
                Arguments.of(new Category(0, VALID_NAME), EntityNotFound.class),
                Arguments.of(new Category(5, VALID_NAME), EntityNotFound.class),
                Arguments.of(new Category(5, null), BadRequest.class)
        );
    }
}
