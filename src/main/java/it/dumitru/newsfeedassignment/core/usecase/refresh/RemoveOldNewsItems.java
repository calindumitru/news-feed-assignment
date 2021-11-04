package it.dumitru.newsfeedassignment.core.usecase.refresh;

import java.time.ZonedDateTime;

public interface RemoveOldNewsItems {
    void deleteOlderThan(ZonedDateTime zonedDateTime);
}
