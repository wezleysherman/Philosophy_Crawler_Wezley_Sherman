
public class Main {
	static Crawler pageCrawler = new Crawler();
	static String pageTitle = "Philosophy";
	static String pageUrl = "https://en.wikipedia.org/wiki/Arizona_State_University";
	
	public static void main(String[] args)
	{
		int currentStep = 0;
		boolean foundPage = false;
		while(!foundPage)		
		{	
			if(pageCrawler.crawlCurrentPage(pageUrl, pageTitle) == true)
			{
				foundPage = true;
			}
			
			if(pageCrawler.nextUrl != pageUrl)
			{
				pageUrl = pageCrawler.nextUrl;
				System.out.println("Current Step: " + currentStep + ". On Page: " + pageUrl);
			}
			
			currentStep ++;
		}
		
		System.out.println("It took " + currentStep + " steps to find: " + pageTitle);
	}
}
