package com.staples.dashboard.app.vo;

/**
 * The Class DotcomAcitivityVO.
 * 
 * @author KumBi002
 * @version 1.0 Revision history
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          <table>
 *          <tr>
 *          <td>Version</td>
 *          <td>Date</td>
 *          <td>Author</td>
 *          <td>Description</td>
 *          </tr>
 *          <tr>
 *          <td>1.0</td>
 *          <td>Dec 4, 2015</td>
 *          <td>KumBi002</td>
 *          <td>Initial Draft</td>
 *          </tr>
 *          </table>
 *          </p>
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 */
public class DotcomAcitivityVO {
    private String skuNumber;
    private String itemDescription;
    private String orderContact;
    private String actDate;
    private String thumbnail;
    private String act;
    private String price;
    private String quantity;
    private String dotComPrice;

    /**
     * @return the skuNumber
     */
    public String getSkuNumber() {
	return skuNumber;
    }

    /**
     * @param skuNumber
     *            the skuNumber to set
     */
    public void setSkuNumber(String skuNumber) {
	this.skuNumber = skuNumber;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
	return itemDescription;
    }

    /**
     * @param itemDescription
     *            the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
    }

    /**
     * @return the orderContact
     */
    public String getOrderContact() {
	return orderContact;
    }

    /**
     * @param orderContact
     *            the orderContact to set
     */
    public void setOrderContact(String orderContact) {
	this.orderContact = orderContact;
    }

    /**
     * @return the actDate
     */
    public String getActDate() {
	return actDate;
    }

    /**
     * @param actDate
     *            the actDate to set
     */
    public void setActDate(String actDate) {
	this.actDate = actDate;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
	return thumbnail;
    }

    /**
     * @param thumbnail
     *            the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
	this.thumbnail = thumbnail;
    }

    /**
     * @return the act
     */
    public String getAct() {
	return act;
    }

    /**
     * @param act
     *            the act to set
     */
    public void setAct(String act) {
	this.act = act;
    }

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return dotCom price 
	 */
	public String getDotComPrice() {
		return dotComPrice;
	}

	/**
	 * @param dotComPrice to set
	 */
	public void setDotComPrice(String dotComPrice) {
		this.dotComPrice = dotComPrice;
	}
	
	
}
