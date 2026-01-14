# ***Notification Package Structure***

## command
- ### application
  - #### controller
  - #### dto
  - #### service
- ### domain
  - #### aggregate
    - Notification(Entity)
    - NotificationType(Entity)
  - #### repository
    - NotificationDomainRepository
    - NotificationTypeDomainRepository
  - #### service
- ### infrastructure
  - #### repository
    - JpaNotificationRepository(extends JpaRepository<>, NotificationDomainRepository)
    - JpaNotificationTypeDomainRepository(extends JpaRepository<>,NotificationTypeDomainRepository)
  - #### service

---

## query
- ### controller
- ### dto
- ### mapper
- ### service