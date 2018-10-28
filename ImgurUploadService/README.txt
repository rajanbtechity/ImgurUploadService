1. com.leadiq.imgur.resources will contain classes/method which are exposed as rest API
2. From these classes/method we will call different class/method to handle the request as required.
3. com.leadiq.imgur.webservice UploadImageService.java, this is a standalone class for now.
We can run it and upload an image from our desktop to imgur site. Need to expose it as rest api 
by completing UploadImage.java class in com.leadiq.imgur.resources package.

To Do List
1. This upload image works for uploading image from Desktop.
2. Modify it to accept array of url and call UploadImageService.java multiple times.
3. We might need to download an image from a given link and upload that to imgur. For that
need java code to download a file using a link and then call UploadImageService.java.
4. make it multithreaded. Main thread should return the status back to the rest client and 
child thread should continue uploading the image.