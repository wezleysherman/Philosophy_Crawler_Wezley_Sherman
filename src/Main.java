
public class Main {
	static Crawler pageCrawler = new Crawler();
	static String pageTitle = "Philosophy";
	static String pageUrl = "https://en.wikipedia.org/wiki/CodeHS";
	
	public static void main(String[] args)
	{
		int currentStep = 0;
		while(currentStep < 500)		
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
