
package myprojects;

public class Item{
    private String name;
    private double price;
    private float quantity;
    private float stock;
    private String scancode;
    private int itemCode;
    private int itemcode;
private String gen_item_code;
private int Stock_group_ID;
private String Stock_group_Des;
private String PurchaseVat_Des;
private String Sales_Des;
private String Other_Tax_Des;
private String Unit_des;
private String Pack_des;
private double unitcost;
private int purchaseVAT_ID;
private int Sales_ID;
private int Other_Tax_ID;
private double price1;
private double price2;
private double price3;
private double price4;
private double price5;
private float Qty_on_order;
private boolean ispromote;
private double prom_price;
private double leadtime;
private double Maxstock;
private double Minstock;
private String Purchase_account="";
private String Sales_account="";
private String Stock_value_account="";
    
 public Item(){
    name="";
    price=0.00;
   quantity=0;
   stock=0;
   scancode="";
   itemCode=0;
 }
 public Item(int new_itemcode,String new_gen_itemcode,String newScancode,String newName,
        String new_Stock_GroupDes,String newUnitDes,
        String newPackDes,String newPurchaseVat_Des,String newSalesDes,String newOtherTax_Des,
        double new_unitcost,double newPrice1,double newPrice2,double newPrice3,double newPrice4,
        double newPrice5,float newQty_on_order,boolean new_isPromote,double newProm_price,double new_leadTime,
        double new_Maxstock,double new_Minstock,String new_Purchase_acc,String new_Sales_acc,
String new_stock_value_acc
        ){
set_Item_Code(new_itemcode);
set_gen_Itemcode(new_gen_itemcode);
SetName(newName);
setScancode(newScancode);
setUnitCost(new_unitcost);
set_StockGrp_Des(new_Stock_GroupDes);
set_PurchaseVat_Des(newPurchaseVat_Des);
set_Sales_Des(newSalesDes);
set_Unit_Des(newUnitDes);
set_Pack_Des(newPackDes);
set_Other_Tax_Des(newOtherTax_Des);
setPrice1(newPrice1);
setPrice2(newPrice2);
setPrice3(newPrice3);
setPrice4(newPrice4);
setPrice5(newPrice5);
setQty_on_order(newQty_on_order);
setIsPromote(new_isPromote);
setPromPrice(newProm_price);
setLeadTime(new_leadTime);
setMaxstock(new_Maxstock);
setMinstock(new_Minstock); 
setPurchaseAccount(new_Purchase_acc);
setSalesAccount(new_Sales_acc);
setStockValueAccount(new_stock_value_acc);
}
 public Item(String newName,double newPrice,float newQuantity,float newStock,String newScancode){
     setName(newName);
     setPrice(newPrice);
     setQuantity(newQuantity);
     setStock(newStock);
     setScancode(newScancode);
 }
 
 public void setName(String newName){
     name=newName;
 }
 
 public void setPrice(double newPrice){
     price=newPrice;
 }
 
 public void setQuantity(float newQuantity){
     quantity=newQuantity;
 }
 
 public void setStock(float newStock){
     stock=newStock;
 }
 
 public void setScancode(String newScancode){
     scancode=newScancode;
 }
 
public void set_Item_Code(int new_item_code){
    itemcode=new_item_code;
}
public void set_gen_Itemcode(String new_gen_Itemcode){
    gen_item_code=new_gen_Itemcode;
}
public void SetName(String newName){
    name=newName;
}

public void set_Stock_Group_ID(int new_stockGrpID){
    Stock_group_ID=new_stockGrpID;
}
public void setUnitCost(double new_unitcost){
 unitcost=new_unitcost;   
}
public void set_StockGrp_Des(String newStockGrp_Des){
    Stock_group_Des=newStockGrp_Des;
}
public void set_PurchaseVat_Des(String new_PurchaseVat_Des){
    PurchaseVat_Des=new_PurchaseVat_Des;
}
public void set_Sales_Des(String new_Sales_Des){
    Sales_Des=new_Sales_Des;
}
public void set_Other_Tax_Des(String new_OtherTax_Des){
    Other_Tax_Des=new_OtherTax_Des;
}
public void setPurchaseVAT_ID(int newPurchaseVAT_ID){
    purchaseVAT_ID=newPurchaseVAT_ID;
}
public void set_Unit_Des(String newUnitDes){
    Unit_des=newUnitDes;
}
public void set_Pack_Des(String newPackDes){
    Pack_des=newPackDes;
}
public void setSales_ID(int newSalesID){
    Sales_ID=newSalesID;
}
public void setOther_Tax_ID(int newOther_Tax_ID){
    Other_Tax_ID=newOther_Tax_ID;
}
public void setPrice1(double newPrice1){
    price1=newPrice1;
}
public void setPrice2(double newPrice2){
    price2=newPrice2;
}
public void setPrice3(double newPrice3){
    price3=newPrice3;
}
public void setPrice4(double newPrice4){
    price4=newPrice4;
}
public void setPrice5(double newPrice5){
    price5=newPrice5;
}
public void setQty_on_order(float newQty_on_order){
    Qty_on_order=newQty_on_order;
}
public void setIsPromote(boolean new_isPromote){
    ispromote=new_isPromote;
}
public void setPromPrice(double newProm_price){
    prom_price=newProm_price;
}
public void setLeadTime(double new_leadTime){
    leadtime=new_leadTime;
}
public void setMaxstock(double new_Maxstock){
    Maxstock=new_Maxstock;
}
public void setMinstock(double new_Minstock){
    Minstock=new_Minstock;
}
public void setPurchaseAccount(String new_Purchase_Acc){
    Purchase_account=new_Purchase_Acc;
}
public void setSalesAccount(String new_Sales_Acc){
    Sales_account=new_Sales_Acc;
}
public void setStockValueAccount(String new_stockvalue_acc){
    Stock_value_account=new_stockvalue_acc;
}
 public String getName(){
     return name;
 }
 
 public double getPrice(){
     return price;
 }
 
 public float getQuantity(){
     return quantity;
 }
 
 public float getStock(){
     return stock;
 }
 
 public String getScancode(){
     return scancode;
 }
 public int getItemCode(){
     return itemCode;
 }

public String get_gen_Itemcode(){
    return gen_item_code;
}

public int getStock_grp_ID(){
    return Stock_group_ID;
}
public String get_StockGrp_Des(){
    return Stock_group_Des;
}
public String get_PurchaseVat_Des(){
    return PurchaseVat_Des;
}
public String get_SalesVat_Des(){
    return Sales_Des;
}
public String getOtherTax_Des(){
    return Other_Tax_Des;
}
public double getUnitCost(){
    return unitcost;
}
public int getPurchaseVAT_ID(){
    return purchaseVAT_ID;
}
public int getSales_ID(){
    return Sales_ID;
}
public String getUnitDes(){
    return Unit_des;
}
public String getPackDes(){
    return Pack_des;
}
public double getOther_Tax_ID(){
    return Other_Tax_ID;
}
public double getPrice1(){
    return price1;
}
public double getPrice2(){
    return price2;
}
public double getPrice3(){
    return price3;
}
public double getPrice4(){
    return price4;
}
public double getPrice5(){
    return price5;
}
public float getQty_on_order(){
    return Qty_on_order;
}
public boolean getIsPromote(){
    return ispromote;
}
public double getPromPrice(){
    return prom_price;
}
public double getLeadTime(){
    return leadtime;
}
public double getMaxStock(){
    return Maxstock;
}
public double getMinStock(){
    return Minstock;
}
public String getPurchaseAccount(){
    return Purchase_account;
}
public String getSalesAccount(){
    return Sales_account;
}
public String get_StockValue_Account(){
    return Stock_value_account;
}
    
}
