package com.sws.newtec.modules.security.dto;

/**
 * Created by Louie on 2015/8/12.
 */
public class CPMenuInfo {
    public String MenuCd;
    public String MenuDesc;
    public String MenuId;
    public String RelatedAsm;
    public String RelatedNS;
    public int SortId;
    public String SuperMenuId;

    public Menu convertToMenuInfo(){
        Menu info=new Menu();
        info.setId(this.MenuId);
        info.setLabel(this.MenuDesc);
        info.setLink(this.RelatedNS);
        info.setIcon(this.RelatedAsm);
        info.setSort(this.SortId);
        return info;
    }
}
