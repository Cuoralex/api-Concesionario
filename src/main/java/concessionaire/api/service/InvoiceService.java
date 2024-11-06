package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.Car;
import concessionaire.api.model.Invoice;
import concessionaire.api.model.Store;
import concessionaire.api.model.User;
import concessionaire.api.repository.CarRepository;
import concessionaire.api.repository.InvoiceRepository;
import concessionaire.api.repository.StoreRepository;
import concessionaire.api.repository.UserRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice createInvoice(Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findByInvoiceId(invoice.getInvoiceId());

        if (existingInvoice != null)
            return null;

        Store store = storeRepository.findById(invoice.getStore().getId()).orElse(null);
        User seller = userRepository.findById(invoice.getSeller().getId()).orElse(null);
        User client = userRepository.findById(invoice.getClient().getId()).orElse(null);
        Car car = carRepository.findById(invoice.getCar().getId()).orElse(null);

        invoice.setStore(store);
        invoice.setSeller(seller);
        invoice.setClient(client);
        invoice.setCar(car);

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Invoice updateInvoice(Long id, Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);

        if (existingInvoice == null)
            return null;

        Store store = storeRepository.findById(invoice.getStore().getId()).orElse(null);
        User seller = userRepository.findById(invoice.getSeller().getId()).orElse(null);
        User client = userRepository.findById(invoice.getClient().getId()).orElse(null);
        Car car = carRepository.findById(invoice.getCar().getId()).orElse(null);

        existingInvoice.setStore(store == null ? existingInvoice.getStore() : store);
        existingInvoice.setSeller(seller == null ? existingInvoice.getSeller() : seller);
        existingInvoice.setClient(client == null ? existingInvoice.getClient() : client);
        existingInvoice.setCar(car == null ? existingInvoice.getCar() : car);
        existingInvoice
                .setInvoiceId(invoice.getInvoiceId() == null ? existingInvoice.getInvoiceId() : invoice.getInvoiceId());
        existingInvoice
                .setQty(invoice.getQty() == null ? existingInvoice.getQty() : invoice.getQty());
        existingInvoice.setPaymentType(
                invoice.getPaymentType() == null ? existingInvoice.getPaymentType() : invoice.getPaymentType());
        existingInvoice.setPaymentStatus(
                invoice.getPaymentStatus() == null ? existingInvoice.getPaymentStatus() : invoice.getPaymentStatus());
        existingInvoice.setDate(invoice.getDate() == null ? existingInvoice.getDate() : invoice.getDate());

        return invoiceRepository.save(existingInvoice);
    }
}
