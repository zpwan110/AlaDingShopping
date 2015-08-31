package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 * 商品信息
 */
public class Product {
    private int pid;//商品Id
    private String name;//产品参考名
    private String icon;//产品大图
    private String iconThumb;//产品小图
    private String brand;//品牌
    private String procurementLocation;//采购地点
    private Double originalPrice;//原价
    private Double price;//实际价
    private Double referencePrice;//参考价
    private String deliveryInformation;//配送信息
    private int browseNumber;//浏览次数
    private int salesNumber;//销售数量
    private int salesRestriction;//销售限制
    private int stock;//产品积分
    private int integral;//
    private int shelve;//
    private String p_icon;//产品产地图片地址
    private int p_oid;//
    private String p_name;//产品产地名

    private int buyNum	;//购买数	number
    private int cid;//
    private String code;//	单位编码
    private String createTime;//	创建时间
    private int deliveryType;//	发货类型
    private String  details;//	详情html
    private Double distributionPrice;//	发货价
    private int evlNum	;//	number
    private String flashSaleIcon;//	特卖图	string
    private String productFlashSale;//	商品特卖
    private ProductOrigin productOrigin;//	原厂地	object
    private String productSn;//	商品条码
    private String promptInfo;//	提示信息	string
    private String rate;//	税
    private Double rateValue;//	税值
    private int scid;//	子分类id
    private String sku;//	sku编码
    private String subtitle;//	子标题	string
    private String tariffsDetail;//	关税明细
    private String updateTime;//	更新时间
    private Double weight;//	净重




    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconThumb() {
        return iconThumb;
    }

    public void setIconThumb(String iconThumb) {
        this.iconThumb = iconThumb;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProcurementLocation() {
        return procurementLocation;
    }

    public void setProcurementLocation(String procurementLocation) {
        this.procurementLocation = procurementLocation;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(String deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public int getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(int browseNumber) {
        this.browseNumber = browseNumber;
    }

    public int getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(int salesNumber) {
        this.salesNumber = salesNumber;
    }

    public int getSalesRestriction() {
        return salesRestriction;
    }

    public void setSalesRestriction(int salesRestriction) {
        this.salesRestriction = salesRestriction;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getShelve() {
        return shelve;
    }

    public void setShelve(int shelve) {
        this.shelve = shelve;
    }

    public String getP_icon() {
        return p_icon;
    }

    public void setP_icon(String p_icon) {
        this.p_icon = p_icon;
    }

    public int getP_oid() {
        return p_oid;
    }

    public void setP_oid(int p_oid) {
        this.p_oid = p_oid;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getDistributionPrice() {
        return distributionPrice;
    }

    public void setDistributionPrice(Double distributionPrice) {
        this.distributionPrice = distributionPrice;
    }

    public int getEvlNum() {
        return evlNum;
    }

    public void setEvlNum(int evlNum) {
        this.evlNum = evlNum;
    }

    public String getFlashSaleIcon() {
        return flashSaleIcon;
    }

    public void setFlashSaleIcon(String flashSaleIcon) {
        this.flashSaleIcon = flashSaleIcon;
    }

    public String getProductFlashSale() {
        return productFlashSale;
    }

    public void setProductFlashSale(String productFlashSale) {
        this.productFlashSale = productFlashSale;
    }

    public ProductOrigin getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(ProductOrigin productOrigin) {
        this.productOrigin = productOrigin;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getPromptInfo() {
        return promptInfo;
    }

    public void setPromptInfo(String promptInfo) {
        this.promptInfo = promptInfo;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getRateValue() {
        return rateValue;
    }

    public void setRateValue(Double rateValue) {
        this.rateValue = rateValue;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getVsubtitle() {
        return subtitle;
    }

    public void setVsubtitle(String vsubtitle) {
        this.subtitle = vsubtitle;
    }

    public String getTariffsDetail() {
        return tariffsDetail;
    }

    public void setTariffsDetail(String tariffsDetail) {
        this.tariffsDetail = tariffsDetail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
