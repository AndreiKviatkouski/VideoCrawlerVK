package by.AndreiKviatkouski;

import by.AndreiKviatkouski.entyties.Video;
import by.AndreiKviatkouski.action.DownloaderManagerAction;
import by.AndreiKviatkouski.service.SpiderService;
import by.AndreiKviatkouski.util.Writer;
import org.jsoup.select.Elements;

import java.util.List;

import static by.AndreiKviatkouski.util.ColorScheme.*;


public class SpiderRun {

    public static void main(String[] args) {

        SpiderService spiderService = new SpiderService();

        Elements onIndexPage = spiderService
                .crawl("https://vk.com/videos-111905078?section=album_273",
                ".video_item_info");

        List<Video> startLinks = spiderService.createStartLinksList(onIndexPage);
        List<Video> modifiedLinks = spiderService.modifyLinksList(startLinks);


        Writer.writeString(PURPLE_BOLD + "Files for download: " + modifiedLinks.size() + " elements " + RESET);
        modifiedLinks.stream().map(video -> video.getName() + " " + video.getUrl()).forEach(System.out::println);

        List<Video> finishList = spiderService.getFinishDownloadList(modifiedLinks);
        DownloaderManagerAction downloaderManager = new DownloaderManagerAction();
        downloaderManager.setFinishList(finishList);


        /**
         * #1 downloads with fore stream
         */
        downloaderManager.startTreads();

        /**
         * # 2downloads with parallel streams
         */

//        finishList.parallelStream()
//                .forEach(video -> downloader.downloadVideo(
//                        video.getDownloadLink(),
//                        "src\\main\\java\\by\\AndreiKviatkouski\\video\\" + video.getName()));


        /**
         * # 3 sequential download of files in a single stream
         */

//        for (Video video : finishList) {
//            Writer.writeString(RED + video + RESET);
//            downloader.setUrl(video.getDownloadLink());
//            downloader.setFileName("src\\main\\java\\by\\AndreiKviatkouski\\video\\" + video.getName());
//            downloader.downloadVideo();
//
//        }
    }
}