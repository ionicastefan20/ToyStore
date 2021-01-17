package com.toy_store.java.command_prompt;


import com.toy_store.java.marketing.Store;
import com.toy_store.java.production.ProductNotFoundException;
import com.toy_store.java.utilities.PriceFormatUtility;
import java.util.List;
import static java.lang.System.*;

/**
 * Command class that calculates the price of the products given as arguments.
 */
public class CalculateTotal implements Command {

    /**
     * The <code>List</code> of products (only their IDs).
     */
    private final List<String> idList;

    /**
     * Constructor of the <code>CalculateTotal</code> class.
     * @param idList the list of IDs.
     */
    public CalculateTotal(List<String> idList) {
        this.idList = idList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Store store = Store.getInstance();
        try {
            out.println(PriceFormatUtility.getPriceAsString(store.calculateTotal(idList),
                    store.getCurrency()));
        } catch (ProductNotFoundException e) {
            out.println(e.getMessage());
        }
    }
}
