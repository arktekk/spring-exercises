package no.arktekk.training.spring.domain;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "auctions")
public class AuctionList {

	private Collection<Auction> auctions;

	public AuctionList() {
	}

	public AuctionList(Collection<Auction> auctions) {
		this.auctions = auctions;
	}

	@XmlElements({ @XmlElement(name = "auction", type = Auction.class) })
	public Collection<Auction> getAuctions() {
		return auctions;
	}

	public void setItems(Collection<Auction> auctions) {
		this.auctions = auctions;
	}
}
