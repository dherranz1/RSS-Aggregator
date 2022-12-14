package com.dherranz1.app

class DataChangesNotifier {
    companion object{
        private val repositoryObservers = mutableListOf<RepositoryObserver>()

        fun subscribe(repositoryObserver: RepositoryObserver) =
            repositoryObservers.add(repositoryObserver)

        fun unSubscribe(repositoryObserver: RepositoryObserver) =
            repositoryObservers.remove(repositoryObserver)

        fun notifyNewChanges() =
            notifyChangesToAllSubscribers()

        private fun notifyChangesToAllSubscribers() =
            repositoryObservers.forEach { observer ->
                observer.notifyChanges()
            }
    }
}