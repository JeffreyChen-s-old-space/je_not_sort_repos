class Base_Observer:

    def __init__(self):
        pass

    class Observer:
        def register(self, listener):
            raise NotImplementedError("Must subclass me")

        def deregister(self, listener):
            raise NotImplementedError("Must subclass me")

        def notify_listeners(self, event):
            raise NotImplementedError("Must subclass me")

    class Listener:
        def __init__(self, name, Object):
            self.name = name
            Object.register(self)

        def notify(self, event):
            print(self.name, "received event", event)

    class Object(Observer):
        def __init__(self):
            self.listeners = []
            self.data = None

        def action(self, event):
            self.data = event
            return self.data

        def register(self, listener):
            self.listeners.append(listener)

        def deregister(self, listener):
            self.listeners.remove(listener)

        def notify_listeners(self, event):
            for listener in self.listeners:
                listener.notify(event)

