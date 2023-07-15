  import com.telesign.MessagingClient;
import com.telesign.RestClient;

  public class HelloTelesign {

      public static void main(String[] args) {

          String customerId = "D9035502-E6A4-4652-8657-8250B5EA2364";
          String apiKey = "+diu/tO35e6lZIExMTGI7VxSqmq1hui6MgHJPmkrsMnWKkY4vssbrJ8ODUw82UEI0epURNVTUSDra77hxnmdAw==";
          String phoneNumber = "5581985312446";
          String message = "You're scheduled for a dentist appointment at 2:30PM.";
          String messageType = "ARN";

          try {
              MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
              RestClient.TelesignResponse telesignResponse = messagingClient
            		  .message(phoneNumber, message, messageType, null);
              System.out.println("Your reference id is: "+telesignResponse.json.get("reference_id"));
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }