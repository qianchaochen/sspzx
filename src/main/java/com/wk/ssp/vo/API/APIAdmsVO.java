package com.wk.ssp.vo.API;

import java.util.List;

import com.wk.ssp.vo.sdk.SdkMixVO;
import com.wk.ssp.vo.sdk.SdkNativeVO;

/**
 * @description: 
 */
public class APIAdmsVO {

    /** 广告位ID **/
    private String adslot_id;
    
    private int ad_type;
    
    /** 创意类型 **/
    private int creative_type;
    
    /** 交互类型 **/
    private int interaction_type;
    
    /** 下载类广告应用包名 **/
    private String bundle;
    
    /** 创意图片地址 **/
    private String imgurl;
    
    /** 素材宽度 **/
    private int w;
    
    /** 素材高度 **/
    private int h;
    
    /** 广告点击跳转地址 **/
    private String clkurl;
    
    /** 曝光追踪地址 **/
    private List<String> imptrackers;
    
    /** 点击追踪地址 **/
    private List<String> clktrackers;
    
    /** H5创意 **/
    private SdkMixVO mix;
    
    /** 图文混合类创意 **/
    private SdkNativeVO nativ;
    
    /** 下载追踪地址 **/
    private List<String> dwnltrackers;
    
    /** 安装追踪地址 **/
    private List<String> intltrackers;
    
    /** 激活追踪地址 **/
    private List<String> actvtrackers;
    
    /** 下载开始监播 **/
    private List<String> dwnlst;

    public String getAdslot_id() {
        return adslot_id;
    }

    public void setAdslot_id(String adslot_id) {
        this.adslot_id = adslot_id;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public int getCreative_type() {
        return creative_type;
    }

    public void setCreative_type(int creative_type) {
        this.creative_type = creative_type;
    }

    public int getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(int interaction_type) {
        this.interaction_type = interaction_type;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getClkurl() {
        return clkurl;
    }

    public void setClkurl(String clkurl) {
        this.clkurl = clkurl;
    }

    public List<String> getImptrackers() {
        return imptrackers;
    }

    public void setImptrackers(List<String> imptrackers) {
        this.imptrackers = imptrackers;
    }

    public List<String> getClktrackers() {
        return clktrackers;
    }

    public void setClktrackers(List<String> clktrackers) {
        this.clktrackers = clktrackers;
    }

    public SdkMixVO getMix() {
        return mix;
    }

    public void setMix(SdkMixVO mix) {
        this.mix = mix;
    }

    public SdkNativeVO getNativ() {
        return nativ;
    }

    public void setNativ(SdkNativeVO nativ) {
        this.nativ = nativ;
    }

    public List<String> getDwnltrackers() {
        return dwnltrackers;
    }

    public void setDwnltrackers(List<String> dwnltrackers) {
        this.dwnltrackers = dwnltrackers;
    }

    public List<String> getIntltrackers() {
        return intltrackers;
    }

    public void setIntltrackers(List<String> intltrackers) {
        this.intltrackers = intltrackers;
    }

    public List<String> getActvtrackers() {
        return actvtrackers;
    }

    public void setActvtrackers(List<String> actvtrackers) {
        this.actvtrackers = actvtrackers;
    }

    public List<String> getDwnlst() {
        return dwnlst;
    }

    public void setDwnlst(List<String> dwnlst) {
        this.dwnlst = dwnlst;
    }
}
