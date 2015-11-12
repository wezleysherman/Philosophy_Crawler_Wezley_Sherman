
public class Main {
	static Crawler pageCrawler = new Crawler();
	static String pageTitle = "Philosophy";
	static String pageUrl = "https://en.wikipedia.org/wiki/CodeHS";
	
	public static void main(String[] args)
	{
		pageCrawler.visitedPages.add(pageUrl);
		int currentStep = 0;
		while(true)		
		{
			if(pageCrawler.crawlCurrentPage(pageUrl, pageTitle) == true)
			{
				break;
			}
			
			if(pageCrawler.nextUrl != pageUrl)
			{
				pageUrl = pageCrawler.nextUrl;
			}
			System.out.println(pageCrawler.nextUrl);
			System.out.println(currentStep);
			currentStep ++;
		}
	}
}
