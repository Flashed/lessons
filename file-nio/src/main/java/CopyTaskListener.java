public  interface CopyTaskListener {
  void onSuccess(CopyTask task);
  void onError(Exception e, CopyTask task);
}