package vn.codegym.model;

public class Product {
    private int id_product;
    private String name_product;
    private long price_product;
    private String image_product;
    private String description;
    private boolean available;
    private int id_category;

    public Product() {
    }

    public Product(int id_product, String name_product, long price_product, String image_product, String description, boolean available, int id_category) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.available = available;
        this.id_category = id_category;
    }

    public Product(int id_product, String name_product, long price_product, String image_product, String description, boolean available) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.available = available;
    }

    public Product(String name_product, long price_product, String image_product, String description, boolean available, int id_category) {
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.available = available;
        this.id_category = id_category;
    }

    public Product(int id_product, String name_product, long price_product, int id_category) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.id_category = id_category;
    }

    public Product(int id_product, String name_product, long price_product, String description, boolean available, int id_category) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.description = description;
        this.available = available;
        this.id_category = id_category;
    }

    public Product(int id_product, String name_product, long price_product, String image_product, String description, int id_category) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.id_category = id_category;
    }

    public Product(String name_product, long price_product, int id_category) {
        this.name_product = name_product;
        this.price_product = price_product;
        this.id_category = id_category;
    }

    public Product(String name_product, long price_product, String image_product, String description, int id_category) {
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.id_category = id_category;
    }

    public Product(String name_product, long price_product, String image_product, String description, boolean available) {
        this.name_product = name_product;
        this.price_product = price_product;
        this.image_product = image_product;
        this.description = description;
        this.available = available;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public long getPrice_product() {
        return price_product;
    }

    public void setPrice_product(long price_product) {
        this.price_product = price_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "product{" +
                "id_product=" + id_product +
                ", name_product='" + name_product + '\'' +
                ", price_product=" + price_product +
                ", image_product='" + image_product + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", id_category=" + id_category +
                '}';
    }
}
