package hello;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


@SpringBootApplication
public class YahooStockAPI {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(YahooStockAPI.class, args);
	
		
		YahooStockAPI yahooStockAPI = new YahooStockAPI();
		//System.out.println(yahooStockAPI.getStock("GOOG"));//BABA,INTC

		//String[] stockNames = { "GOOG", "INTC", "BABA", "YHOO" };
		//System.out.println(yahooStockAPI.getStock(stockNames));
	
		//yahooStockAPI.getHistory("GOOG");

		yahooStockAPI.getHistory("INTC", 2, "Daily");
		
	}

	/*private Stock getStock(String stock) throws IOException {
		return YahooFinance.get(stock);
	}*/
	
	/*private StockDto getStock(String stockName) throws IOException {
		StockDto dto=null;
		Stock stock = YahooFinance.get(stockName);
		dto=new StockDto(stock.getName(),stock.getQuote().getPrice(),stock.getQuote().getChange(),stock.getCurrency(),stock.getQuote().getBid());
		return dto; 
	}*/
	
	private Map<String, Stock> getStock(String[] stockName) throws IOException {
		Map<String, Stock> stock = YahooFinance.get(stockName);
		return stock; 
	}
	
	public void getHistory(String stockName) throws IOException {

		Stock stock = YahooFinance.get(stockName);
		List<HistoricalQuote> history = stock.getHistory();
		for (HistoricalQuote quote : history) {
			System.out.println("====================================");
			System.out.println("symobol : " + quote.getSymbol());
			System.out.println("date : " + convertDate(quote.getDate()));
			System.out.println("High Price  : " + quote.getHigh());
			System.out.println("Low Price : " + quote.getLow());
			System.out.println("Closed price : " + quote.getClose());
			System.out.println("=========================================");
		}

	}
	
	private String convertDate(Calendar cal) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String formatDate = format.format(cal.getTime());
		return formatDate;
	}

	
	public void getHistory(String stockName, int year, String searchType) throws IOException {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, Integer.valueOf("-" + year));

		Stock stock = YahooFinance.get(stockName);
		List<HistoricalQuote> history = stock.getHistory(from, to, getInterval(searchType));
		for (HistoricalQuote quote : history) {
			System.out.println("====================================");
			System.out.println("symobol : " + quote.getSymbol());
			System.out.println("date : " + convertDate(quote.getDate()));
			System.out.println("High Price  : " + quote.getHigh());
			System.out.println("Low Price : " + quote.getLow());
			System.out.println("Closed price : " + quote.getClose());
			System.out.println("=========================================");
		}

	}
	
	private Interval getInterval(String searchType) {
		Interval interval = null;
		switch (searchType.toUpperCase()) {
		case "MONTHLY":
			interval = Interval.MONTHLY;
			break;
		case "WEEKLY":
			interval = Interval.WEEKLY;
			break;
		case "DAILY":
			interval = Interval.DAILY;
			break;

		}
		return interval;
	}

}
