## 메인 스레드와 백그라운드 스레드의 분리

Background thread is much suitable for data fetching or running service which doesn't require any UI change.

Working with LiveData, there are two different way to update its value.

(setValue() - using main thread / postValue() - using background thread)
