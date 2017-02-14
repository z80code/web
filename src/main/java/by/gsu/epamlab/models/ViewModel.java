package by.gsu.epamlab.models;

public class ViewModel {
 /* a returned object have next structure:
 @fields: status  = "error"|"ok",
 message = "text of the message if an error detected",
 data    = {}
 */
    private String status;
    private String message;
    private Object data;

    public ViewModel(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
