package by.AndreiKviatkouski.action;

import by.AndreiKviatkouski.entyties.Video;
import by.AndreiKviatkouski.service.DownloadService;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class DownloaderManagerAction {

    private List<Video> finishList;


    ExecutorService executor = Executors.newFixedThreadPool(4);

    public void startTreads() {

        for (Video video : finishList) {
            DownloadService downloader = new DownloadService();
            Thread thread = new Thread(downloader);
            downloader.setUrl(video.getDownloadLink());
            downloader.setFileName("src\\main\\java\\by\\AndreiKviatkouski\\video2\\" + video.getName());
            executor.execute(thread);

        }
        executor.shutdown();
    }
}
