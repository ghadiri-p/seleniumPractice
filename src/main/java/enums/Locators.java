package enums;
    public enum Locators {
        WEBSITE_URL("http://demo-store.seleniumacademy.com ")
        ,ACCOUNT("//span[@class = 'label' and text()='Account']")
        ,LOGIN("//div[@id='header-account']/descendant::a[@title='Log In']")
        ,USER_NAME("//ul[@class='form-list']/descendant::*[@id='email']")
        , PASSWORD("//ul[@class='form-list']/descendant::*[@id='pass']")
        , LOGIN_BUTTON("//button[@type='submit']/descendant::*[text()='Login']")
        ,MESSAGE("//li[@class='error-msg']/descendant::span")
        , DASHBOARD("//div[@class='page-title']")
        ,MENS_LABEL("//ol[@class='nav-primary']/li[@class='level0 nav-2 parent']")
        ,NEW_ARRIVALS("(//ul[@class='level0']/descendant::a[text()='New Arrivals'])[2]")
        ,CATEGORY_PRODUCTS_TOOLBAR("(//div[@class='toolbar']//div)[3]")
        ,PRODUCTS("//*[@class ='products-grid products-grid--max-4-col first last odd']")
        , VIEW_DETAIL_BUTTON("(//a[@title='View Details'])[2]")
        , KHAKI_BOWERY("//span[text()='Khaki Bowery Chino Pants']")
        , COLOR("//ul[@id='configurable_swatch_color']/descendant::*[@alt='Khaki']")
        , SIZE("//ul[@id='configurable_swatch_size']/descendant::*[@name='30']")
        , QTY("//input[@title='Qty']")
        , PRICE("//div[@class='price-info']/descendant::span[text()='$140.00']")
        , ADD_TO_CART("(//button[@class='button btn-cart'])[2]")
        ,CART("//*[text() = 'Cart']")
        ,VIEW_SHOPPING_CART("View Shopping Cart")
        ,PRODUCT_NAME("//*[@class ='product-cart-info']/child::*[@class = 'product-name']")
        ,PRODUCT_COLOR("//*[@class ='product-cart-info']/child::*[@class = 'item-options']/dd[1]")
        ,PRODUCT_SIZE("//*[@class ='product-cart-info']/child::*[@class = 'item-options']/dd[2]")
        ,PRODUCT_PRICE("//span[@class='cart-price']//span")
        ,PRODUCT_COUNT("//*[@class='product-cart-actions']/input")
        ,PRODUCT_TOTAL_PRICE("//*[@class='product-cart-total']/span[@class='cart-price']/span[@class='price']")
        ,CART_COUNT("//div[@class='header-minicart']//*[@class='count']")
        ,VIEW_SHOPPING_CARTS("//div[@class='minicart-actions']/a")
        ,EMPTY_CART("//td[@class='a-right cart-footer-actions last']/descendant::*[text()='Empty Cart']");

        private final String value;

        Locators(String value) {
            this.value = value;
        }


        public String getValue() {
            return value;
        }

    }

