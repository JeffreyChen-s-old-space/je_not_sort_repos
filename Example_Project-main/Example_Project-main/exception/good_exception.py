
def verify_status(status: str):
    if not status == "status_verified":
        raise Exception("status not verified")
