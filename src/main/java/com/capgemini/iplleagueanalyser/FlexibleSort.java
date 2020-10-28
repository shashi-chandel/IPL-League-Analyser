package com.capgemini.iplleagueanalyser;

import java.util.Comparator;

public class FlexibleSort implements Comparator<Batting> {
	public enum Order {
		AVG, SR, BOUNDARIES, SR_AND_BOUNDARIES, AVG_AND_SR
	}

	public Order sortingBy;

	public FlexibleSort(Order sortingBy) {
		this.sortingBy = sortingBy;
	}

	@Override
	public int compare(Batting b1, Batting b2) {
		switch (sortingBy) {
		case AVG:
			if (b1.getAvg().contains("-"))
				b1.setAvg("0");
			return (int) (Double.parseDouble(b2.getAvg()) - Double.parseDouble((b1.getAvg())));
		case SR:
			if (b1.getStrikeRate().contains("-"))
				b1.setStrikeRate("0");
			return (int) (Double.parseDouble(b2.getStrikeRate()) - Double.parseDouble((b1.getStrikeRate())));
		case BOUNDARIES:
			return (Integer.parseInt(b2.getFours()) + Integer.parseInt(b2.getSixes()))
					- (Integer.parseInt(b1.getFours()) + Integer.parseInt(b1.getSixes()));
		case SR_AND_BOUNDARIES:
			if (b1.getStrikeRate().contains("-"))
				b1.setStrikeRate("0");
			double value = Double.parseDouble(b2.getStrikeRate()) - Double.parseDouble((b1.getStrikeRate()));
			if (value == 0) {
				return (Integer.parseInt(b2.getFours()) + Integer.parseInt(b2.getSixes()))
						- (Integer.parseInt(b1.getFours()) + Integer.parseInt(b1.getSixes()));
			} else if (value > 0)
				value = 1;
			else if (value < 0)
				value = -1;
			return (int) value;
		case AVG_AND_SR:
			if (b1.getAvg().contains("-"))
				b1.setAvg("0");
			value = (Double.parseDouble(b2.getAvg()) - Double.parseDouble((b1.getAvg())));
			if (value == 0) {
				return (int) (Double.parseDouble(b2.getStrikeRate()) - Double.parseDouble((b1.getStrikeRate())));
			} else if (value > 0)
				value = 1;
			else if (value < 0)
				value = -1;
			return (int) value;
		default:
			break;
		}
		return 0;
	}
}