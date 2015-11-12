import java.io.IOException;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {
	private static String CRAWLER_AGENT = "Philosophy Crawler";
	private Document doc;
	public String nextUrl;
	
	public boolean crawlCurrentPage(String url, String findName)
	{
		try
		{
			Connection con = Jsoup.connect(url).userAgent(CRAWLER_AGENT);
			Document curDoc = con.get();
			this.doc = curDoc;
			
			String html = doc.body().toString();
			Document docment = Jsoup.parse(html);
			int index = 0;
			boolean foundLink = false;
			while(!foundLink)
			{
				Element link = docment.select("p").select("a").get(index);
				String linkAttr = link.attr("href");
				
				if(!linkAttr.toLowerCase().contains("//") && linkAttr.toLowerCase().contains("/wiki/") && 
						!linkAttr.toLowerCase().contains("help") && !linkAttr.toLowerCase().contains("file"))
				{
					nextUrl = "https://en.wikipedia.org" + linkAttr;
					foundLink = true;
					if(linkAttr.contains(findName))
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
	
	public String getPageTitle()
	{
		return this.doc.title().toLowerCase();
	}
}
