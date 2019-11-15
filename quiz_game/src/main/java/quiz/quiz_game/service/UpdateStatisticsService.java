package quiz.quiz_game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.quiz_game.model.Statistics;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.StatisticsRepository;

@Service
public class UpdateStatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public void updateGoodA(User user) {
        Statistics statistics = findStatistics(user);
        if (statistics != null) {
            int gA = statistics.getGoodAnswers();
            gA += 1;
            statistics.setGoodAnswers(gA);
            saveStatistics(statistics);
        }
    }

    public void updateBadA(User user) {
        Statistics statistics = findStatistics(user);
        if (statistics != null) {
            int bA = statistics.getBadAnswers();
            bA += 1;
            statistics.setBadAnswers(bA);
            saveStatistics(statistics);
        }
    }

    public void updateWin(User user) {
        Statistics statistics = findStatistics(user);
        if (statistics != null) {
            int win = statistics.getWin();
            win += 1;
            statistics.setWin(win);
            saveStatistics(statistics);
        }
    }

    public void updateLost(User user) {
        Statistics statistics = findStatistics(user);
        if (statistics != null) {
            int lost = statistics.getLost();
            lost += 1;
            statistics.setLost(lost);
            saveStatistics(statistics);
        }
    }

    private Statistics findStatistics(User user) {
        return statisticsRepository.findByUser(user);
    }

    private void saveStatistics(Statistics statistics) {
        statisticsRepository.save(statistics);
    }
}
