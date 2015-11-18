
public class Main {
	static Crawler pageCrawler = new Crawler();
	static String pageTitle = "Philosophy";
	static String pageUrl = "https://en.wikipedia.org/wiki/Arizona_State_University";
	
	/* The main function of the program will create a while loop
	 * that keeps feeding the page crawler the first link it grabs
	 * until the page crawler returns true and has found the desired web page.
	 */
	public static void main(String[] args)
	{
		int currentStep = 0;
		boolean foundPage = false;
		while(!foundPage)		
		{	
			currentStep ++;
			if(pageCrawler.crawlCurrentPage(pageUrl, pageTitle) == true)
			{
				foundPage = true;
			}
			pageUrl = pageCrawler.nextUrl;
			System.out.println("Current Step: " + currentStep + ". On Page: " + pageUrl);
		}
		System.out.println("It took " + currentStep + " steps to find: " + pageTitle);
	}
}
