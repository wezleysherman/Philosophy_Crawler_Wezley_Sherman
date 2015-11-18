import java.io.IOException;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {
	private static String CRAWLER_AGENT = "Philosophy Crawler";
	private Document doc;
	public String nextUrl;
	
	/* This method takes in a url string, and a page name string. It will then
	 * crawl the given page for the first link, check that link, and return true if the link
	 * contains the name of the given page, and returns false with a new page url if the crawler is
	 * unable to find the page with the desired name. 
	 */
	public boolean crawlCurrentPage(String url, String findName)
	{	
		//We use a try/catch here in case Jsoup is unable to connect to the page.
		try
		{
			String urlOfFindName = "/wiki/" + findName;
			int index = 0;
			boolean foundLink = false;
			
			Connection con = Jsoup.connect(url).userAgent(CRAWLER_AGENT);
			Document curDoc = con.get();
			this.doc = curDoc;
			
			String html = doc.body().toString();
			Document docment = Jsoup.parse(html);
			
			while(!foundLink)
			{
				Element link = docment.select("p").select("a").get(index);
				String linkWithCaps = link.attr("href");
				String linkToScan = linkWithCaps.toLowerCase();
				
				if(!linkToScan.contains("//") && !linkToScan.contains("help") && 
						!linkToScan.contains("file") && linkToScan.contains("/wiki/"))
				{
					nextUrl = "https://en.wikipedia.org" + linkWithCaps;
					foundLink = true;
					
					if(linkWithCaps.contains(urlOfFindName))
					{
						return true;
					}
				}
				index++;
			}
			return false;
		}
		catch(IOException e)
		{
			return false;
		}
	}
}
