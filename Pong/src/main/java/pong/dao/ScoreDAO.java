
package pong.dao;

import java.util.List;
import pong.domain.Score;

public interface ScoreDAO {
    Score create(Score score) throws Exception;
    List<Score> getAll() throws Exception;
}