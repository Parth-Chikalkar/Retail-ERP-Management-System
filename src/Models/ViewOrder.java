package Models;

public class ViewOrder {
    private int oid;
    private String product_name ;
    private int quantity ;
    private int total ;
    private String customer_name ;
    private int cu_id;
    public ViewOrder(int oid , String customer_name , String product_name , int quantity , int total , int cu_id){
        this.oid = oid;

        this.product_name = product_name;
        this.quantity = quantity;
        this.total = total;
        this.customer_name=customer_name;
        this.cu_id=cu_id;


    }

    public ViewOrder (String product_name , int quantity , int total , String customer_name){
        this.product_name = product_name;
        this.quantity = quantity;
        this.total = total;
        this.customer_name = customer_name;
    }
    // Getters
    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }

    public String getProduct_name() {
        return this.product_name ;
    }



    public int getOid() {
        return oid;
    }

    public int getCu_id() {
        return cu_id;
    }

    public String getCustomer_name() {
        return this.customer_name;

    }

}
