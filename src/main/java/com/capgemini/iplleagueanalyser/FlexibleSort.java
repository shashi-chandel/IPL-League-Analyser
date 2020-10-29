package com.capgemini.iplleagueanalyser;

import java.util.Comparator;

public class FlexibleSort<T> implements Comparator<T> {
	public enum Order {
		BAT_AVG, BAT_SR, BOUNDARIES, SR_AND_BOUNDARIES, AVG_AND_SR, RUNS_AND_AVG, BOWL_AVG, BOWL_SR, ECONOMY,
		BOWL_SR_AND_WICKETS
	}

	public Order sortingBy;

	public FlexibleSort(Order sortingBy) {
		this.sortingBy = sortingBy;
	}

	@Override
	public int compare(T o1, T o2) {
		Batting bat1 = null, bat2 = null;
		Bowling bowl1 = null, bowl2 = null;
		if (o1.getClass().equals(Batting.class)) {
			bat1 = (Batting) o1;
			bat2 = (Batting) o2;
		} else if (o1.getClass().equals(Bowling.class)) {
			bowl1 = (Bowling) o1;
			bowl2 = (Bowling) o2;
		}
		switch (sortingBy) {
		case BAT_AVG:
			if ((bat1).getAvg().contains("-"))
				bat1.setAvg("0");
			return (int) (Double.parseDouble(bat2.getAvg()) - Double.parseDouble((bat1.getAvg())));
		case BAT_SR:
			if (bat1.getStrikeRate().contains("-"))
				bat1.setStrikeRate("0");
			return (int) (Double.parseDouble(bat2.getStrikeRate()) - Double.parseDouble((bat1.getStrikeRate())));

		case BOUNDARIES:
			return (Integer.parseInt(bat2.getFours()) + Integer.parseInt(bat2.getSixes()))
					- (Integer.parseInt(bat1.getFours()) + Integer.parseInt(bat1.getSixes()));
		case SR_AND_BOUNDARIES:
			if (bat1.getStrikeRate().contains("-"))
				bat1.setStrikeRate("0");
			double value = Double.parseDouble(bat2.getStrikeRate()) - Double.parseDouble((bat1.getStrikeRate()));
			if (value == 0) {
				return (Integer.parseInt(bat2.getFours()) + Integer.parseInt(bat2.getSixes()))
						- (Integer.parseInt(bat1.getFours()) + Integer.parseInt(bat1.getSixes()));
			}
			value = setValue(value);
			return (int) value;
		case AVG_AND_SR:
			if (bat1.getAvg().contains("-"))
				bat1.setAvg("0");
			value = Double.parseDouble(bat2.getAvg()) - Double.parseDouble((bat1.getAvg()));
			if (value == 0) {
				return (int) (Double.parseDouble(bat2.getStrikeRate()) - Double.parseDouble((bat1.getStrikeRate())));
			}
			value = setValue(value);
			return (int) value;
		case RUNS_AND_AVG:
			if (bat1.getAvg().contains("-"))
				bat1.setAvg("0");
			value = Integer.parseInt(bat2.getRuns()) - Integer.parseInt(bat1.getRuns());
			if (value == 0) {
				return (int) (Double.parseDouble(bat2.getAvg()) - Double.parseDouble((bat1.getAvg())));
			}
			value = setValue(value);
			return (int) value;
		case BOWL_AVG:
			if (bowl1.getAvg().contains("-"))
				bowl1.setAvg("999999");
			return (int) (Double.parseDouble(bowl1.getAvg()) - Double.parseDouble((bowl2.getAvg())));
		case BOWL_SR:
			if (bowl1.getStrikeRate().contains("-"))
				bowl1.setStrikeRate("999999");
			return (int) (Double.parseDouble(bowl1.getStrikeRate()) - Double.parseDouble((bowl2.getStrikeRate())));
		case ECONOMY:
			return (int) setValue(Double.parseDouble(bowl1.getEconomy()) - Double.parseDouble((bowl2.getEconomy())));
		case BOWL_SR_AND_WICKETS:
			if (bowl1.getStrikeRate().contains("-"))
				bowl1.setStrikeRate("999999");
			value = setValue(Double.parseDouble(bowl1.getStrikeRate()) - Double.parseDouble((bowl2.getStrikeRate())));
			if (value == 0) {
				return ((Integer.parseInt(bowl1.getFiveWickets()) + Integer.parseInt(bowl1.getFourWickets()))
						- (Integer.parseInt(bowl2.getFiveWickets()) + Integer.parseInt(bowl2.getFourWickets())));
			}
			return (int) value;
		default:
			break;

		}
		return 0;
	}

	public double setValue(double value) {
		if (value > 0)
			value = 1;
		else if (value < 0)
			value = -1;
		return value;
	}
}