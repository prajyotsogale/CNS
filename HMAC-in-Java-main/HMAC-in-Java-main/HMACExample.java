import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HMACExample {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the secret key: ");
        String secretKey = scanner.nextLine();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        try {
            byte[] secretKeyBytes = secretKey.getBytes("UTF-8");
            byte[] messageBytes = message.getBytes("UTF-8");

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            byte[] hmacBytes = mac.doFinal(messageBytes);

            // Convert the HMAC bytes to a hexadecimal string
            StringBuilder result = new StringBuilder();
            for (byte b : hmacBytes) {
                result.append(String.format("%02x", b));
            }

            System.out.println("HMAC: " + result.toString());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: HmacSHA256 algorithm not available.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
