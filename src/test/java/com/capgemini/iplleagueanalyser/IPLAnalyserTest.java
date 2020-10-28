package com.capgemini.iplleagueanalyser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPLAnalyserTest {
	private static final String BATTING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\batting.csv";
	private static final String BOWLING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\bowling.csv";
	private IPLAnalyser iplAnalyser;
	List<Batting> sortedBattingList;

	@Before
	public void init() {
		iplAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenBattingDataCSVFile_ShouldLoadBattingData() throws IPLAnaylserException {
		int totalRecords = iplAnalyser.loadBattingData(BATTING_DATA_PATH);
		assertEquals(101, totalRecords);
	}

	@Test
	public void givenBowlingDataCSVFile_ShouldLoadBowlingData() throws IPLAnaylserException {
		int totalRecords = iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
		assertEquals(99, totalRecords);
	}

	@Test
	public void givenBattingData_WhenSortedByAvg_ShouldReturnHighestAvgFirst() throws IPLAnaylserException {
		iplAnalyser.loadBattingData(BATTING_DATA_PATH);
		sortedBattingList = iplAnalyser.getSortedData(FlexibleSort.Order.AVG);
		assertEquals("83.2", sortedBattingList.get(0).getAvg());
	}

	@Test
	public void givenBattingData_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IPLAnaylserException {
		iplAnalyser.loadBattingData(BATTING_DATA_PATH);
		sortedBattingList = iplAnalyser.getSortedData(FlexibleSort.Order.SR);
		assertEquals("333.33", sortedBattingList.get(0).getStrikeRate());
	}
}
