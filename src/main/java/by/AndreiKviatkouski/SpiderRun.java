package by.AndreiKviatkouski;

import by.AndreiKviatkouski.entyties.Video;
import by.AndreiKviatkouski.action.DownloaderManagerAction;
import by.AndreiKviatkouski.service.SpiderService;
import by.AndreiKviatkouski.service.StatisticsService;
import by.AndreiKviatkouski.util.Writer;
import by.AndreiKviatkouski.validator.PropertiesValidator;
import org.jsoup.select.Elements;

import java.nio.file.Path;
import java.util.List;

import static by.AndreiKviatkouski.util.ColorScheme.*;


public class SpiderRun {

    public static void main(String[] args) {

        SpiderService spiderService = new SpiderService();

        /**
        * crawling start page
        */
        Elements onIndexPage = spiderService
                .crawl("https://vk.com/videos-111905078?section=album_273",
                        ".video_item_info");
        /**
         * getting links on start page and modifying by conditions
         */
        List<Video> startLinks = spiderService.createStartLinksList(onIndexPage);
        List<Video> modifiedLinks = spiderService.modifyLinksList(startLinks);

        /**
         * printing information in console
         */
        Writer.writeString(PURPLE_BOLD + "Files for download: " + modifiedLinks.size() + " elements " + RESET);
        modifiedLinks.stream().map(video -> video.getName() + " " + video.getUrl()).forEach(System.out::println);

        /**
         * crawling pages from  finish download list
         */
        List<Video> finishList = spiderService.getFinishDownloadList(modifiedLinks);
        DownloaderManagerAction downloaderManager = new DownloaderManagerAction();
        downloaderManager.setFinishList(finishList);
        /**
         * printing information in statistics file
         */
        StatisticsService statisticsService = new StatisticsService();
        Path outputFile = PropertiesValidator.chekPath("src\\main\\java\\by\\AndreiKviatkouski\\video2\\statistics.txt");
        statisticsService.writeToFile(outputFile, finishList);

        /**
         * #1 downloads with fore stream
         */
//        downloaderManager.startTreads();

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