/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myprojects;


public class StockGroups {
String group_name;
int group_Id;

public StockGroups(){
    group_name="";
    group_Id=0;
}
public StockGroups(String new_group_name,int new_group_Id){
    set_StockGroup_name(new_group_name);
    set_GroupID(new_group_Id);
}
public void set_StockGroup_name(String new_group_name){
    group_name=new_group_name;
}
public void set_GroupID(int new_group_id){
    group_Id=new_group_id;
}
public String get_StockGroup_Name(){
    return group_name;
}
public int get_StockGroup_Id(){
    return group_Id;
}
}
