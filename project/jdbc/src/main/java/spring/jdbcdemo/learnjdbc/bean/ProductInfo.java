package spring.jdbcdemo.learnjdbc.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ProductInfo
 */
public class ProductInfo {
	private String productId;
	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品价格
	 */
	private BigDecimal productPrice;

	/**
	 * 库存
	 */
	private Integer productStock;

	/**
	 * 描述
	 */
	private String productDescription;

	/**
	 * 小图
	 */
	private String productIcon;

	/**
	 * 产品状态(商品状态,0正常1下架)
	 */
	private Integer productStatus;

	/**
	 * 类目编号
	 */
	private Integer categoryType;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the productStock
	 */
	public Integer getProductStock() {
		return productStock;
	}

	/**
	 * @param productStock the productStock to set
	 */
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the productIcon
	 */
	public String getProductIcon() {
		return productIcon;
	}

	/**
	 * @param productIcon the productIcon to set
	 */
	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}

	/**
	 * @return the productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the categoryType
	 */
	public Integer getCategoryType() {
		return categoryType;
	}

	/**
	 * @param categoryType the categoryType to set
	 */
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return productId;
	}
}