package FacturadeDB.Database.DB_Management;

import FacturadeDB.Facturade.Client.client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Transactional
@Repository
interface MyRepository extends CrudRepository<client, Long> {

}