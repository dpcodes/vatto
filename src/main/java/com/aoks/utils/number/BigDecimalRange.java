package com.aoks.utils.number;

import java.math.BigDecimal;

import javax.persistence.Column;

public class BigDecimalRange implements Range<BigDecimal> {

	@Column(name = "cBegin")
    private BigDecimal start;
    
    @Column(name = "cEnd")
    private BigDecimal end;

	@Override
	public Range<BigDecimal> start(BigDecimal unit) {
		this.start = unit;
		return this;
	}

	@Override
	public BigDecimal start() {
		return start;
	}

	@Override
	public Range<BigDecimal> end(BigDecimal unit) {
		this.end = unit;
		return this;
	}

	@Override
	public BigDecimal end() {
		return end;
	}

	@Override
	public boolean includes(BigDecimal unit) {
		if (unit == null) return false;
		if (start == null) return true;
		return unit.doubleValue() >= start.doubleValue() &&
			(end == null ? true : (unit.doubleValue() <= end.doubleValue()));
	}

}
