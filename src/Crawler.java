import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private static String CRAWLER_AGENT = "Philosophy Crawler";
	private Document doc;
	public String nextUrl;
	public List<String> visitedPages = new LinkedList();
	
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
			while(true)
			{
				if(visitedPages.size() < 1)
				{
					visitedPages.add(url);
				}
				
				//for(int i = 0; i < visitedPages.size(); i++)
				//{
					//System.out.println(docment.body());
					Element link = docment.body().select("a").get(index);
					String linkAttr = link.attr("href");
					System.out.println(linkAttr);
					if(!visitedPages.get(0).contains(linkAttr))
					{	
						if(!linkAttr.toLowerCase().contains("//") && linkAttr.toLowerCase().contains("/wiki/") && !linkAttr.toLowerCase().contains("file"))
						{
							nextUrl = "https://en.wikipedia.org" + linkAttr;
							System.out.println(nextUrl);
							visitedPages.add(nextUrl);
							break;
						}
					//}
				}
				index++;
			}
			
			boolean containsName = getPageTitle() == findName.toLowerCase();
			if(containsName)
			{
				return true;
			}
			else
			{
				return false;
			}
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
