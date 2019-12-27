package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@org.springframework.stereotype.Repository
@Transactional
public interface Repository extends CrudRepository<client, Long> {

}