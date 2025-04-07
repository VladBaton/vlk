CREATE OR REPLACE FUNCTION notify_new_payment()
            RETURNS TRIGGER AS $name_tag$
            DECLARE
            BEGIN
            IF NEW.status = 1 THEN
                INSERT INTO NOTIFICATION (contractId, eventCode, message)
                    VALUES (
                        NEW.contractId,
                        5,
                        '”спешна€ оплата периода с номером ' || NEW.orderNum ||
                        ', начало действи€: ' || TO_CHAR(NEW.startDate, 'YYYY-MM-DD')
                );
            END IF;
            RETURN NEW;
            END;
            $name_tag$ LANGUAGE plpgsql;

create OR REPLACE TRIGGER new_payment_trigger
AFTER UPDATE ON PAYMENT
FOR EACH ROW
EXECUTE FUNCTION notify_new_payment();